package ru.database.coursework.api.team_member.model;

import java.sql.Date;

public record TeamMemberCreationRequest(
        int employeeId,
        Date startDate,
        Date endDate
) {

}
