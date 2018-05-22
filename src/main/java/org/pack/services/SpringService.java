package org.pack.services;

import org.pack.services.dto.AbstractDTO;

public interface SpringService {

	Object processRequest(ServiceRequestDTO<? extends AbstractDTO> serviceRequestDTO);
}
