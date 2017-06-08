package services;

import config.RepositoryConfig;
import epam.beans.Organization;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.implementation.OrganizationServiceImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(SpringJUnit4ClassRunner.class)
public class OrganizationServiceMockitoTest extends RepositoryConfig {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @InjectMocks
    OrganizationServiceImpl organizationServiceMock;


    @Test
    public void testGetAllOrganizations_returnList() {

        List<Organization> organizations = organizationServiceMock.getAll();
        verify(organizationRepositoryMock).findAll();
        assertNotNull(organizations);

    }


    @Test
    public void testGetOrganization_whenStringParameter_returnOrganization() {
        Organization organization = organizationServiceMock.get(anyString());
        verify(organizationRepositoryMock).findByName(anyString());
        assertNotNull(organization);
        assertEquals(organization.getName(), "test");

    }

    @Test
    public void testGetOrganization_whenLongArgument_returnOrganization() {
        Organization organization = organizationServiceMock.get(anyLong());
        verify(organizationRepositoryMock).findOne(anyLong());
        assertNotNull(organization);
        assertEquals(organization.getName(), "test");

    }

    @Test
    public void testDeleteOrganizationById_throwException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Test organization delete by id exception");
        organizationServiceMock.delete(anyLong());
        verify(organizationRepositoryMock, times(1)).delete(anyLong());

    }

    @Test
    public void testDeleteOrganizationByObject_throwException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Test organization delete by Organization object exception");
        organizationServiceMock.delete(new Organization());
        verify(organizationRepositoryMock, times(1)).delete(any(Organization.class));

    }

    @Test
    public void testCreateOrganization_whenOrganizationArgument_throwException() {

        exception.expect(RuntimeException.class);
        exception.expectMessage("Test create and update exception");
        organizationServiceMock.create(any(Organization.class));
        verify(organizationRepositoryMock).save(any(Organization.class));

    }


}
