#FROM maven:3.9.6-eclipse-temurin-21 AS build
#LABEL authors="mehdizaidane"
#
#WORKDIR /app
#
#COPY pom.xml .
#
#COPY . /app
#
#RUN mvn package
#
#CMD ["java", "-jar", "target/shoppingCart.jar"]

#FROM maven:3.9.6-eclipse-temurin-21 AS build
#ENV DISPLAY=host.docker.internal:0.0
#
#RUN apt-get update && \
#    apt-get install -y wget unzip libgtk-3-0 libgbm1 libx11-6 && \
#    apt-get clean
#
#RUN wget https://download2.gluonhq.com/openjfx/21/openjfx-21_linux-x64_bin-sdk.zip -O /tmp/openjfx.zip && \
#    unzip /tmp/openjfx.zip -d /opt && \
#    rm /tmp/openjfx.zip
#
#WORKDIR /app
#
#COPY pom.xml .
#COPY src ./src
#
#
#RUN mvn clean package -DskipTests
#
#RUN ls -l target/
#
#CMD ["java", "--module-path", "/opt/javafx-sdk-21/lib", "--add-modules", "javafx.controls,javafx.fxml", "-jar", "target/shoppingCart.jar"]

# ---------------------------
# Stage 1: Build the JAR
# ---------------------------
FROM eclipse-temurin:21-jdk AS build

# Install dependencies for Maven build and gui.gui
RUN apt-get update && \
    apt-get install -y maven wget unzip libgtk-3-0 libgbm1 libx11-6 && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Download JavaFX SDK
RUN wget https://download2.gluonhq.com/openjfx/21/openjfx-21_linux-x64_bin-sdk.zip -O /tmp/openjfx.zip && \
    unzip /tmp/openjfx.zip -d /opt && \
    rm /tmp/openjfx.zip

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the fat/shaded JAR
RUN mvn clean package -DskipTests

# ---------------------------
# Stage 2: Runtime image
# ---------------------------
FROM eclipse-temurin:21-jre

# Install gui.gui dependencies
RUN apt-get update && \
    apt-get install -y libgtk-3-0 libgbm1 libx11-6 \
    fonts-noto-cjk fonts-ipafont fonts-unfonts-core \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy the fat JAR and JavaFX SDK
COPY --from=build /app/target/shoppingCart-1.0-SNAPSHOT-shaded.jar ./shoppingCart.jar
COPY --from=build /opt/javafx-sdk-21 /opt/javafx-sdk-21

# Set DISPLAY for X11 forwarding
ENV DISPLAY=:0

# Run the app
CMD ["java", "-Dfile.encoding=UTF-8", "--module-path", "/opt/javafx-sdk-21/lib", "--add-modules", "javafx.controls,javafx.fxml", "-jar", "shoppingCart.jar"]