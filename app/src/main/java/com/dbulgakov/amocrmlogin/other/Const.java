package com.dbulgakov.amocrmlogin.other;

public interface Const {
    String UI_THREAD = "UI_THREAD";
    String IO_THREAD = "IO_THREAD";
    String BASE_URL = "https://www.amocrm.ru/";
    String RESPONSE_TYPE = "json";
    String ACCOUNT_TYPE = "com.dbulgakov";
    String USER_ACCOUNT_DOMAIN_KEY = "USER_ACCOUNT_DOMAIN_KEY";
    int MIN_PASS_LENGTH = 6;
    String GET_LEADS_STRING = "https://%s.amocrm.ru/private/api/v2/json/leads/list?type=json&USER_LOGIN=%s&USER_HASH=%s";
}
