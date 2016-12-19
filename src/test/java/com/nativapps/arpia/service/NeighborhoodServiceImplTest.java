package com.nativapps.arpia.service;

import com.nativapps.arpia.database.dao.NeighborhoodDao;
import com.nativapps.arpia.database.entity.Neighborhood;
import com.nativapps.arpia.model.ConfigLanguage;
import com.nativapps.arpia.model.dto.NeighborhoodRequest;
import com.nativapps.arpia.model.dto.NeighborhoodResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NeighborhoodServiceImplTest {

    private static NeighborhoodDao dao;
    private NeighborhoodServiceImpl serviceImpl;
    private static String neighborhoodName;
    private static Neighborhood example;
    private static Neighborhood result;
    private static NeighborhoodRequest request;
    private static NeighborhoodResponse response;
    private static List<Neighborhood> listExample;
    private static List<NeighborhoodResponse> listResponse;

    @BeforeClass
    public static void setUpClass() {
        dao = mock(NeighborhoodDao.class);

        neighborhoodName = "value";
        example = new Neighborhood(neighborhoodName);
        result = new Neighborhood(1l, neighborhoodName);

        request = new NeighborhoodRequest(neighborhoodName);
        response = new NeighborhoodResponse(1l, neighborhoodName);

        listExample = Arrays.asList(result);
        listResponse = Arrays.asList(response);

        when(dao.findAll(0, 10)).thenReturn(listExample);
        when(dao.find(1l)).thenReturn(result);
        when(dao.find(2l)).thenReturn(null);
        when(dao.save(example)).thenReturn(result);
        when(dao.save(result)).thenReturn(result);
    }

    @Before
    public void setUp() throws IOException {
        serviceImpl = new NeighborhoodServiceImpl(dao);
        serviceImpl.config = new ConfigLanguage();
    }

    /**
     * Successful getAll
     */
    @Test
    public void testGetAllCase1() {
        assertEquals(serviceImpl.getAll(0, 10), listResponse);
    }
    
    /**
     * Start param < 0
     */
    @Test(expected = BadRequestException.class)
    public void testGetAllCase2() {
        assertEquals(serviceImpl.getAll(-1, 10), listResponse);
    }

    /**
     * Size param <= 0
     */
    @Test(expected = BadRequestException.class)
    public void testGetAllCase3() {
        assertEquals(serviceImpl.getAll(0, 0), listResponse);
    }

    /**
     * Successful get
     */
    @Test
    public void testGetCase1() {
        assertEquals(serviceImpl.get(1l), response);
    }

    /**
     * ID param = null
     */
    @Test(expected = BadRequestException.class)
    public void testGetCase2() {
        assertEquals(serviceImpl.get(null), response);
    }

    /**
     * ID param <= 0 
     */
    @Test(expected = BadRequestException.class)
    public void testGetCase3() {
        assertEquals(serviceImpl.get(-1l), response);
    }

    /**
     * ID param not found
     */
    @Test(expected = NotFoundException.class)
    public void testGetCase4() {
        assertEquals(serviceImpl.get(2l), response);
    }

    /**
     * Successful add
     */
    @Test
    public void testAddCase1() {
        assertEquals(serviceImpl.add(request), response);
    }

    /**
     * Data param = null
     */
    @Test(expected = BadRequestException.class)
    public void testAddCase2() {
        assertEquals(serviceImpl.add(null), response);
    }

    /**
     * Neighborhood name is already exists
     */
    @Test(expected = BadRequestException.class)
    public void testAddCase3() {
        when(dao.findByName(neighborhoodName)).thenReturn(result);
        assertEquals(serviceImpl.add(request), response);
    }

    /**
     * Neighborhood name = null
     */
    @Test(expected = BadRequestException.class)
    public void testAddCase4() {
        assertEquals(serviceImpl.add(new NeighborhoodRequest()), response);
    }

    /**
     * Successful update
     */
    @Test
    public void testUpdateCase1() {
        assertEquals(serviceImpl.update(1l, request),response);
    }
    
    /**
     * Data param = null
     */
    @Test(expected = BadRequestException.class)
    public void testUpdateCase2() {
        assertEquals(serviceImpl.update(1l, null),response);
    }
    
    /**
     * Neighborhood name = null
     */
    @Test(expected = BadRequestException.class)
    public void testUpdateCase3() {
        assertEquals(serviceImpl.update(1l, new NeighborhoodRequest()),response);
    }

    @Test
    public void testConvertToEntity() {
        assertEquals(serviceImpl.convertToEntity(request), example);
    }

    @Test
    public void testConvertToDto() {
        assertEquals(serviceImpl.convertToDto(result), response);
    }

    /**
     * Successful get by name
     */
    @Test
    public void testGetByNameCase1() {
        when(dao.findByName(neighborhoodName)).thenReturn(result);
        assertEquals(serviceImpl.getByName(neighborhoodName), response);
    }
    
    @Test(expected = BadRequestException.class)
    public void testGetByNameCase2() {
        assertEquals(serviceImpl.getByName(null), response);
    }
    
    @Test(expected = NotFoundException.class)
    public void testGetByNameCase3() {
        when(dao.findByName(neighborhoodName)).thenReturn(null);
        assertEquals(serviceImpl.getByName(neighborhoodName), response);
    }
}
