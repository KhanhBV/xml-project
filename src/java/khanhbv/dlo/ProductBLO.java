/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhbv.dlo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import khanhbv.dto.ProductTestDTO;
import khanhbv.entities.Brand;
import khanhbv.entities.Category;
import khanhbv.entities.Product;
import khanhbv.utils.StringConstant;

/**
 *
 * @author vankhanhbui
 */
public class ProductBLO {

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

    public boolean checkProductExist(String productName) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select p from Product p "
                + "Where p.name = :productName";
        Query query = em.createQuery(jpql);
        query.setParameter("productName", productName);
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

    public Product getProductByName(String productName) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT p FROM Product p WHERE p.name = :productName";
        Query query = em.createQuery(jpql);
        query.setParameter("productName", productName);
        try {
            List<Product> result = query.getResultList();
            if (result != null && !result.isEmpty()) {
                return result.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public void updateProduct(Product product) {
        EntityManager em = emf.createEntityManager();

        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(product);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void insertProduct(Product product) {
        EntityManager em = emf.createEntityManager();
        Product resultProdut = getProductByName(product.getName());
        try {
            if (resultProdut == null) {
                em.getTransaction().begin();
                em.persist(product);
                em.getTransaction().commit();

            } else {

//                if (product.getImageURL() == null) {
//                    product.setImageURL(StringConstant.UPDATE_STRING);
//                }
//                if (product.getName() == null) {
//                    return;
//                }
                resultProdut.setImageURL(product.getImageURL());
                resultProdut.setCategoryID(product.getCategoryID());
                resultProdut.setName(product.getName());
                resultProdut.setBrandID(product.getBrandID());
                resultProdut.setPower(product.getPower());
                resultProdut.setUrl(product.getUrl());
                updateProduct(resultProdut);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Brand checkBrandOfProduct(String name, List<Brand> listBrand) {
        boolean check = false;
        try {

            if (listBrand != null) {
                for (int i = 0; i < listBrand.size(); i++) {
                    if (name.toUpperCase().contains(listBrand.get(i).getName().toUpperCase())) {
                        check = true;
                        return listBrand.get(i);
                    }
                }
            }
            if (!check) {
                for (int i = 0; i < listBrand.size(); i++) {
                    if (listBrand.get(i).getName().equals("KHÁC")) {
                        return listBrand.get(i);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Category checkCtegoryOfProduct(String name, List<Category> listCategory) {
//        boolean check = false;
        try {

            if (listCategory != null) {
                for (int i = 0; i < listCategory.size(); i++) {
                    if (name.toUpperCase().contains(listCategory.get(i).getName().toUpperCase())) {
//                        check = true;
                        return listCategory.get(i);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getAllProduct() {
        EntityManager em = emf.createEntityManager();

        try {
            String jpql = "Product.findAll";
            Query query = em.createNamedQuery(jpql);
            List<Product> listProduct = query.getResultList();
            return listProduct;

        } catch (Exception e) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<ProductTestDTO> searchProductByCaAndBrand(String brandName, String categoryName, String nameProduct) {
        EntityManager em = emf.createEntityManager();
        try {
            String jdql = "SELECT p.id, p.name, b.name, p.imageURL, p.power, p.url, c.name "
                    + "FROM ((Product p INNER JOIN Brand b ON p.brandID = b.id) INNER JOIN Category c ON p.categoryID = c.id) "
                    + "WHERE b.name LIKE N'%" + brandName + "%' AND c.name LIKE N'%" + categoryName + "%' AND p.name LIKE N'%" + nameProduct + "%'";
            System.out.println(jdql);
            List<Object[]> result = em.createNativeQuery(jdql).getResultList();
            List<ProductTestDTO> listProduct = new ArrayList<>();
            for (Object[] rs : result) {
                ProductTestDTO dto = new ProductTestDTO();
                dto.setId(rs[0].toString());
                dto.setName(rs[1].toString());
                dto.setBrandID(rs[2].toString());
                dto.setImageURL(rs[3].toString());
                dto.setPower(rs[4].toString());
                dto.setLinkProduct(rs[5].toString());
                dto.setCategoryID(rs[6].toString());
                
                listProduct.add(dto);
            }
            return listProduct;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

}
