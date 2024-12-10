package ru.database.coursework.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import ru.database.coursework.core.Entity.AuthEntity;
import ru.database.coursework.core.Entity.UserPrivileges;

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
            SELECT * FROM users_privileges JOIN public.menu m on users_privileges.menu_id = m.id WHERE user_id=:user_id;
            """)
    List<UserPrivileges> findUserPrivileges(@Bind("user_id") int userId);
}
