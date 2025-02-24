package com.server.backend.service;


import com.server.backend.entity.Organization;

import java.util.List;
import java.util.Map;

public interface OrganizationService {
    List<Map<String,Object>> getAllOrgInfo();
    Organization createOrg(Organization org );
    List<Organization> getAllOrg();
    Organization deleteOrg(long id);
    Organization updateOrg(Organization address , long id);
}
