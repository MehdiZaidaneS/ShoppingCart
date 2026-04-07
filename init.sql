-- Create database
CREATE DATABASE IF NOT EXISTS shopping_cart_localization
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE shopping_cart_localization;

-- Create cart_records table
CREATE TABLE IF NOT EXISTS cart_records (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            total_items INT NOT NULL,
                                            total_cost DOUBLE NOT NULL,
                                            language VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Create cart_items table
CREATE TABLE IF NOT EXISTS cart_items (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          cart_record_id INT,
                                          item_number INT NOT NULL,
                                          price DOUBLE NOT NULL,
                                          quantity INT NOT NULL,
                                          subtotal DOUBLE NOT NULL,
                                          FOREIGN KEY (cart_record_id) REFERENCES cart_records(id) ON DELETE CASCADE
    );

-- Create localization_strings table
CREATE TABLE IF NOT EXISTS localization_strings (
                                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                                    `key` VARCHAR(100) NOT NULL,
    value VARCHAR(255) NOT NULL,
    language VARCHAR(10) NOT NULL
    );

-- Insert localization strings
INSERT INTO localization_strings (`key`, value, language) VALUES
-- 🇺🇸 ENGLISH
('prompt1', 'Enter number of items:', 'en'),
('prompt2', 'Enter price for item:', 'en'),
('prompt3', 'Enter quantity for item:', 'en'),
('prompt4', 'Total cost:', 'en'),
('button1', 'Enter input', 'en'),
('calculateTotal', 'Calculate Total', 'en'),

-- 🇦🇪 ARABIC
('prompt1', 'أدخل عدد العناصر:', 'ar'),
('prompt2', 'أدخل سعر العنصر:', 'ar'),
('prompt3', 'أدخل كمية العنصر:', 'ar'),
('prompt4', 'إجمالي التكلفة:', 'ar'),
('button1', 'أدخل البيانات', 'ar'),
('calculateTotal', 'احسب الإجمالي', 'ar'),

-- 🇫🇮 FINNISH
('prompt1', 'Syötä ostettavien tuotteiden määrä:', 'fi'),
('prompt2', 'Syötä tuotteen hinta:', 'fi'),
('prompt3', 'Syötä tuotteen määrä:', 'fi'),
('prompt4', 'Kokonaishinta:', 'fi'),
('button1', 'Syötä syöte', 'fi'),
('calculateTotal', 'Laske kokonaissumma', 'fi'),

-- 🇯🇵 JAPANESE
('prompt1', '購入する商品の数を入力してください:', 'ja'),
('prompt2', '商品の価格を入力してください:', 'ja'),
('prompt3', '商品の数量を入力してください:', 'ja'),
('prompt4', '合計金額:', 'ja'),
('button1', '入力してください', 'ja'),
('calculateTotal', '合計を計算する', 'ja'),

-- 🇸🇪 SWEDISH
('prompt1', 'Ange antalet varor att köpa:', 'sv'),
('prompt2', 'Ange priset för varan:', 'sv'),
('prompt3', 'Ange mängden varor:', 'sv'),
('prompt4', 'Total kostnad:', 'sv'),
('button1', 'Ange inmatning', 'sv'),
('calculateTotal', 'Beräkna totalt', 'sv');