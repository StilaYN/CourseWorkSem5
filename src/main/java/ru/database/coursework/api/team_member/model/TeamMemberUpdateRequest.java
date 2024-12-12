package ru.database.coursework.api.team_member.model;

import java.sql.Date;

public record TeamMemberUpdateRequest(
        int id,
        int employeeId,
        Date startDate,
        Date endDate,
        boolean delete
) {

}
