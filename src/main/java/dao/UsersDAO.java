package dao;



import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;


public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public UsersDataSet getByEmail(String email){
        CriteriaBuilder criteriaBuilder= session.getCriteriaBuilder();
        CriteriaQuery<UsersDataSet> criteriaQuery = criteriaBuilder.createQuery(UsersDataSet.class);
        Root<UsersDataSet> root =criteriaQuery.from(UsersDataSet.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"), email));
        /*Query query =session.createQuery(criteriaQuery);
        List<UsersDataSet> result=query.getResultList();
        if(result.size()==0) return null;
        else return result.get(0);*/
        return session.createQuery(criteriaQuery).uniqueResult();
    }

    public void insertUser(UsersDataSet dataSet)  {
        session.persist(dataSet);
    }




}
