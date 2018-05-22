package org.pack.ch9.spring.transactions.hibernate.home;

import org.pack.services.ServiceRequestDTO;
import org.pack.services.dto.ContactDTO;

public class ContactServiceHelper {

	public Contact saveContactInSteps(ServiceRequestDTO<ContactDTO> serviceRequestDTO) {
		ContactService contactService = (ContactService) HelperFactory.INSTANCE.getBean(serviceRequestDTO.getServiceName());
		ContactDTO contactDTO = (ContactDTO) serviceRequestDTO.getDto();
		Contact contact = contactService.findById(contactDTO.getId());
		try {
			contact = contactService.saveContactInSteps(contact, contactDTO.getFirstName(), contactDTO.getLastName(), contactDTO.getBirthDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contact;
	}
	
	/*public Contact saveById(ServiceRequestDTO serviceRequestDTO) {
		
	}*/
	
	public Contact findById(ServiceRequestDTO<ContactDTO> serviceRequestDTO) {
		ContactService contactService = (ContactService) HelperFactory.INSTANCE.getBean(serviceRequestDTO.getServiceName());
		ContactDTO contactDTO = (ContactDTO) serviceRequestDTO.getDto();
		return contactService.findById(contactDTO.getId());
	}
	
	/*public List<Contact> findAll(ServiceRequestDTO serviceRequestDTO) {
		
	}*/
}
