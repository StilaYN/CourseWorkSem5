package ru.database.coursework.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiPaths {

    public static final String ABOUT = "/about";

    public static final String LOGIN = "/";

    public static final String HOMEPAGE = "/homepage";

    public static final String CONTENT = "/content";

    public static final String CITY_LIST = "/city/list";

    public static final String CITY_CREATE = "/city/create";

    public static final String CITY_UPDATE = "/city/update/{id}";

    public static final String BANK_LIST = "/bank/list";

    public static final String BANK_CREATE = "/bank/create";

    public static final String BANK_UPDATE = "/bank/update/{id}";

    public static final String CLIENT_LIST = "/client/list";

    public static final String CLIENT_CREATE = "/client/create";

    public static final String CLIENT_UPDATE = "/client/update/{id}";

    public static final String CHANGE_PASSWORD = "/change/password";

    public static final String DOCUMENT = "/document";

    public static final String DOCUMENT_FILE= "/document/file";

    public static final String DOCUMENT_TYPE_LIST = "/documentType/list";

    public static final String DOCUMENT_TYPE_CREATE = "/documentType/create";

    public static final String DOCUMENT_TYPE_UPDATE = "/documentType/update/{id}";

    public static final String EMPLOYEE_LIST = "/employee/list";

    public static final String EMPLOYEE_CREATE = "/employee/create";

    public static final String EMPLOYEE_UPDATE = "/employee/update/{id}";

    public static final String PROJECT_LIST = "/project/list";

    public static final String PROJECT_CREATE = "/project/create";

    public static final String PROJECT_UPDATE = "/project/update/{id}";

    public static final String SETTINGS = "/settings";

    public static final String SPECIALIZATION_LIST = "/specialization/list";

    public static final String SPECIALIZATION_CREATE = "/specialization/create";

    public static final String SPECIALIZATION_UPDATE = "/specialization/update/{id}";

    public static final String STREET_LIST = "/street/list";

    public static final String STREET_CREATE = "/street/create";

    public static final String STREET_UPDATE = "/street/update/{id}";

    public static final String TEAM_LIST = "/team/list";

    public static final String TEAM_CREATE = "/team/create";

    public static final String TEAM_UPDATE = "/team/update/{id}";

    public static final String TEAM_MEMBER_LIST = "/teamMember/list";

    public static final String TEAM_MEMBER_CREATE = "/teamMember/create";

    public static final String TEAM_MEMBER_UPDATE = "/teamMember/update/{id}";

}
