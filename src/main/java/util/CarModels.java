package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CarModels {

    private static final Set<String> MODELS = Set.of(
            "Toyota Camry", "Honda Civic", "Ford F-150", "Volkswagen Golf", "BMW 3 Series", "Mercedes-Benz C-Class", "Chevrolet Corvette", "Tesla Model 3", "Nissan GT-R",
            "Porsche 911", "Kia Rio", "Hyundai Solaris", "Lada Vesta", "Subaru Forester", "Jeep Wrangler", "Mazda MX-5", "Audi A4", "Lexus RX", "Volvo XC90", "Land Rover Defender",
            "Fiat 500", "Mini Cooper", "Aston Martin DB11", "Ferrari 488 GTB", "Lamborghini Huracán", "McLaren 720S", "Bugatti Chiron", "Toyota Corolla", "Honda CR-V", "Ford Focus",
            "Volkswagen Polo", "BMW X5", "Mercedes-Benz S-Class", "Chevrolet Silverado", "Tesla Model Y", "Nissan Qashqai", "Porsche Cayenne", "Kia Sportage", "Hyundai Tucson",
            "Lada Granta", "Subaru Outback", "Jeep Grand Cherokee", "Mazda CX-5", "Audi Q7", "Lexus ES", "Volvo S60", "Land Rover Discovery", "Fiat Panda", "Mini Countryman",
            "Aston Martin Vantage", "Ferrari SF90 Stradale", "Lamborghini Urus", "McLaren GT", "Toyota Prius", "Honda Accord", "Ford Explorer", "Volkswagen Tiguan", "BMW 5 Series",
            "Mercedes-Benz E-Class", "Chevrolet Camaro", "Tesla Model S", "Nissan Rogue", "Porsche Panamera", "Kia Sorento", "Hyundai Santa Fe", "UAZ Patriot", "Subaru Impreza",
            "Jeep Cherokee", "Mazda 6", "Audi A6", "Lexus GX", "Volvo XC60", "Land Rover Range Rover", "Fiat Ducato", "Mini Clubman", "Aston Martin DBS Superleggera", "Ferrari Roma",
            "Lamborghini Aventador", "McLaren Speedtail", "Toyota Supra", "Honda Pilot", "Ford Mustang", "Volkswagen Jetta", "BMW 7 Series", "Mercedes-Benz GLE", "Chevrolet Tahoe",
            "Tesla Cybertruck", "Nissan Pathfinder", "Porsche Macan", "Kia Telluride", "Hyundai Palisade", "Lada Niva Legend", "Subaru Ascent", "Jeep Renegade", "Mazda CX-9",
            "Audi A8", "Lexus LX", "Volvo V60", "GMC Sierra", "Cadillac Escalade", "Peugeot 208", "Renault Clio", "Opel Astra", "Skoda Octavia", "Acura TLX", "Alfa Romeo Giulia",
            "Bentley Continental GT", "Buick Enclave", "Cadillac CT5", "Chery Tiggo 7 Pro", "Chrysler 300", "Citroën C5 Aircross", "Dodge Challenger", "Genesis G80", "GMC Yukon",
            "Haval H6", "Infiniti Q50", "Jaguar F-PACE", "Kia Carnival", "Lincoln Navigator", "Maserati Ghibli", "Mitsubishi Outlander", "Opel Corsa", "Peugeot 3008", "Polestar 2",
            "Ram 1500", "Renault Arkana", "Rivian R1T", "Saab 9-3", "Saturn Aura", "Seat Ibiza", "Skoda Kodiaq", "Smart Fortwo", "SsangYong Actyon", "Tata Nexon", "VinFast VF 8",
            "Xpeng P7", "Zotye SR9", "Acura MDX", "Alfa Romeo Stelvio", "Bentley Bentayga", "Buick Regal", "Cadillac XT6", "Chery Arrizo 6 Pro", "Chrysler Pacifica", "Citroën C4 Cactus",
            "Dodge Charger", "Genesis GV70", "GMC Acadia", "Haval F7x", "Infiniti QX60", "Jaguar I-PACE", "Kia K5", "Lincoln Aviator", "Maserati Levante", "Mitsubishi Pajero Sport",
            "Opel Insignia", "Peugeot 5008", "Polestar 3", "Renault Captur", "Rivian R1S", "Saab 9-5", "Saturn Outlook", "Seat Leon", "Skoda Superb", "Smart Forfour", "SsangYong Korando",
            "Tata Punch", "VinFast VF 9", "Xpeng G9", "Acura RDX", "Alfa Romeo Tonale", "Bentley Flying Spur", "Buick Encore GX", "Cadillac Lyriq", "Chery Tiggo 8 Pro Max",
            "Chevrolet Equinox", "Chrysler Voyager", "Citroën C3 Aircross", "Dodge Durango", "Genesis GV80", "GMC Canyon", "Haval Jolion", "Infiniti QX80", "Jaguar E-PACE", "Kia EV6",
            "Lincoln Corsair", "Maserati Quattroporte", "Mitsubishi Eclipse Cross", "Opel Grandland", "Peugeot 2008", "Porsche Taycan", "Renault Duster", "Subaru Crosstrek",
            "Suzuki Jimny", "Tesla Model X", "Toyota Highlander", "Volkswagen Arteon", "Vauxhall Corsa", "Wuling Mini EV", "XPeng P5", "Acura Integra", "Alfa Romeo 4C", "Aston Martin Valkyrie"
    );

    public static String[] getModelsSet() {
        return MODELS;
    }
}