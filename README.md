#  Smart Earthquake Emergency Bag Assistant

##Türkçe Açıklama

Bu proje, ailelerin deprem acil durum çantası hazırlamasına yardımcı olan konsol tabanlı bir Java uygulamasıdır.  
Uygulama, nesne yönelimli programlama (OOP) dersinde aldığım prensipleri uygulamak amacıyla geliştirilmiştir.

Özellikler
- Aile bireylerini farklı kategorilere ayırma (Adult, Baby, Elderly, Chronic Patient)
- Risk seviyesine göre (HIGH, MEDIUM, LOW) temel ihtiyaçların otomatik eklenmesi
- Her kategoriye özel kişisel eşyaların dosyalardan yüklenmesi
- Çanta kapasitesinin aşılması durumunda **özel exception (OverweightBagException)** kullanımı
- Dosya tabanlı veri yönetimi (File I/O)
- Enum (`RiskLevel`) kullanımı
- Kullanıcı girdileri için hata kontrolü (input validation)

Kullanılan Kavramlar
- Abstract Class
- Inheritance
- Interface
- Enum
- Custom Exception
- File I/O
- Polymorphism

---

##  English Description

This project is a **console-based Java application** that helps families prepare an **earthquake emergency bag**.  
The application is developed using **object-oriented programming (OOP)** principles.

### Features
- Categorizing family members (Adult, Baby, Elderly, Chronic Patient)
- Automatically adding base items based on risk level (HIGH, MEDIUM, LOW)
- Loading category-specific personal items from external text files
- Handling bag capacity overflow using a **custom exception (OverweightBagException)**
- File-based data management (File I/O)
- Use of `enum` (`RiskLevel`)
- Input validation to prevent invalid user input

### Concepts Used
- Abstract Class
- Inheritance
- Interface
- Enum
- Custom Exception
- File I/O
- Polymorphism

---

## ▶️ How to Run
1. Compile all Java files
2. Make sure the `items/` directory exists and contains the required `.txt` files
3. Run the `Main` class
4. Follow the console menu instructions

---

## 📁 Project Structure
src/
├─ Main.java
├─ Person.java
├─ Adult.java
├─ Baby.java
├─ Elderly.java
├─ ChronicPatient.java
├─ EmergencyBag.java
├─ Item.java
├─ ItemLoader.java
├─ OverweightBagException.java
├─ RiskLevel.java
└─ Weighable.java

items/
├─ base_items_high.txt
├─ base_items_medium.txt
├─ base_items_low.txt
├─ adult_items.txt
├─ baby_items.txt
├─ elderly_items.txt
└─ chronic_items.txt
