package guru.springframework.domain;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jmsantiago on 13/04/2020
 */
public class CategoryTest extends TestCase {

    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void testGetId() {
        Long id = 4L;
        category.setId(id);
        assertEquals(id,category.getId());
    }
    @Test
    public void testGetDescription() {
    }

    @Test
    public void testGetRecipes() {
    }
}