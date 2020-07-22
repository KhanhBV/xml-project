/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.dlo;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import khanhbv.entities.Brand;

/**
 *
 * @author vankhanhbui
 */
public class BrandBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectXMLPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public boolean checkBrandExist(String brandName) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select r From Brand r "
                + "Where r.name = :brandName";
        Query query = em.createQuery(jpql);
        query.setParameter("brandName", brandName.toUpperCase());
        try {
            query.getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void insertBrand(String brandName) {
        EntityManager em = emf.createEntityManager();
        try {
            Brand brand = new Brand();
            brand.setName(brandName.toUpperCase());
            em.getTransaction().begin();
            em.persist(brand);
            em.getTransaction().commit();
        } catch (Exception e) {

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Brand> getAllBrand() {
        EntityManager em = emf.createEntityManager();
        try {

            String jpql = "Brand.findAll";
            Query query = em.createNamedQuery(jpql);
            List<Brand> result = query.getResultList();
            return result;
        } catch (Exception e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }
    public Brand getBrandByID(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            String jpql = "SELECT b FROM Brand b WHERE b.id = :id";
            Query query = em.createQuery(jpql);
            query.setParameter("id", id);
            Brand brand = (Brand) query.getSingleResult();
            return brand;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Brand getBrandByName(String nameBrand) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT b FROM Brand b WHERE b.name = :name";
            Query query = em.createNamedQuery(jpql);
            query.setParameter("name", nameBrand);
            Brand brand = (Brand) query.getSingleResult();
            if (brand != null) {
                return brand;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
