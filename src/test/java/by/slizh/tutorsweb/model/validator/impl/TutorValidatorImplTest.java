package by.slizh.tutorsweb.model.validator.impl;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TutorValidatorImplTest {

    @Test
    public void validatePhoneCorrectTest() {
        String phone = "+375297777777";
        boolean result = TutorValidatorImpl.getInstance().validatePhone(phone);
        assertTrue(result);
    }

    @Test
    public void validatePhoneInCorrectTest() {
        String phone = "+37529777df7777";
        boolean result = TutorValidatorImpl.getInstance().validatePhone(phone);
        assertFalse(result);
    }

    @Test
    public void validateEducationCorrectTest() {
        String education = "BSUIR";
        boolean result = TutorValidatorImpl.getInstance().validateEducation(education);
        assertTrue(result);
    }

    @Test
    public void validateEducationIncorrectTest() {
        String education = "";
        boolean result = TutorValidatorImpl.getInstance().validateEducation(education);
        assertFalse(result);
    }

    @Test
    public void validateInfoCorrectTest() {
        String info = "Подготовка к ЦТ";
        boolean result = TutorValidatorImpl.getInstance().validateInfo(info);
        assertTrue(result);
    }

    @Test
    public void validateInfoIncorrectTest() {
        String info = "";
        boolean result = TutorValidatorImpl.getInstance().validateInfo(info);
        assertFalse(result);
    }

    @Test
    public void validatePriceCorrectTest() {
        String price = "44";
        boolean result = TutorValidatorImpl.getInstance().validatePrice(price);
        assertTrue(result);
    }

    @Test
    public void validatePriceIncorrectTest() {
        String price = "44?";
        boolean result = TutorValidatorImpl.getInstance().validatePrice(price);
        assertFalse(result);
    }

    @Test
    public void validateCityCorrectTest() {
        String city = "Минск";
        boolean result = TutorValidatorImpl.getInstance().validateCity(city);
        assertTrue(result);
    }

    @Test
    public void validateCityIncorrectTest() {
        String city = "$San-Francisco$";
        boolean result = TutorValidatorImpl.getInstance().validateCity(city);
        assertFalse(result);
    }

    @Test
    public void validateCorrectTutorMap() {
        Map<String, String[]> map = new HashMap<>();
        map.put("phone", new String[]{"+375297777777"});
        map.put("city", new String[]{"Гродно"});
        map.put("education", new String[]{"BSUIR"});
        map.put("info", new String[]{"Работаем круглосуточно"});
        map.put("price", new String[]{"23"});
        map.put("subject", new String[]{"1", "10", "11"});
        boolean result = TutorValidatorImpl.getInstance().validateTutorMap(map);
        assertTrue(result);
    }

    @Test
    public void validateIncorrectTutorMap() {
        Map<String, String[]> map = new HashMap<>();
        map.put("phone", new String[]{"+37529777df7777"});
        map.put("city", new String[]{"Гродно"});
        map.put("education", new String[]{""});
        map.put("info", new String[]{"Работаем круглосуточно"});
        map.put("price", new String[]{"23"});
        map.put("subject", new String[]{"1", "10", "11"});
        boolean result = TutorValidatorImpl.getInstance().validateTutorMap(map);
        assertFalse(result);
    }


}