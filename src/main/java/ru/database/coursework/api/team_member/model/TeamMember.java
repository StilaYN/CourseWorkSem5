package ru.database.coursework.api.team_member.model;

import java.sql.Date;

public record TeamMember(
    int id,
    int employeeId,
    String employeeName,
    Date startDate,
    Date endDate
) {

}
