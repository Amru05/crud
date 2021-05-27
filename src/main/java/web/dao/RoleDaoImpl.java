package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getById(Long id) {
        return entityManager.createQuery("FROM Role WHERE id = :id_param", Role.class)
                .setParameter("id_param", id)
                .getSingleResult();
    }

    @Override
    public Role getByName(String name) {
        return entityManager.createQuery("FROM Role WHERE roleName = :roleName_param", Role.class)
                .setParameter("roleName_param", name)
                .getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("FROM Role").getResultList();
    }
}
