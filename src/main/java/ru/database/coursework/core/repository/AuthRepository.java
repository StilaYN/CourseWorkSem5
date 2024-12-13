package ru.database.coursework.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.database.coursework.core.entity.AuthEntity;
import ru.database.coursework.core.entity.UserPrivileges;

import java.util.List;
import java.util.Optional;

@RegisterConstructorMapper(AuthEntity.class)
@RegisterConstructorMapper(UserPrivileges.class)
public interface AuthRepository {

    @SqlQuery("""
            SELECT * FROM users WHERE login=:login AND password = digest(:password, 'sha256')::text;
            """)
    Optional<AuthEntity> findByLogin(@Bind("login") String login, @Bind("password") String password);

    @SqlQuery("""
            SELECT * FROM users WHERE id=:id AND password = digest(:password, 'sha256')::text;
            """)
    Optional<AuthEntity> findById(@Bind("id") int id, @Bind("password") String password);

    @SqlUpdate("""
            UPDATE users SET password=digest(:password, 'sha256')::text WHERE id=:id;
            """)
    void changePassword(@Bind("id") int id, @Bind("password") String newPassword);

    @SqlQuery("""
            SELECT * FROM users_privileges JOIN public.menu m on users_privileges.menu_id = m.id WHERE user_id=:user_id;
            """)
    List<UserPrivileges> findUserPrivileges(@Bind("user_id") int userId);
}
