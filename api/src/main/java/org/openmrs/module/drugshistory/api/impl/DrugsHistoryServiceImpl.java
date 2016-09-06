/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.drugshistory.api.impl;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.drugshistory.api.DrugsHistoryService;
import org.openmrs.module.drugshistory.api.db.DrugsHistoryDAO;

/**
 * It is a default implementation of {@link DrugsHistoryService}.
 */
public class DrugsHistoryServiceImpl extends BaseOpenmrsService implements DrugsHistoryService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private DrugsHistoryDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(DrugsHistoryDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public DrugsHistoryDAO getDao() {
	    return dao;
    }

	@Override
	public List<Obs> getDrugsHistory(Patient patient) {
		// DRUGS DISPENSED = 1282
		List<Obs> drugsHistory = new ArrayList<Obs>();
		Integer drugsConceptId = 1282;
		Concept drugsDispensed = Context.getConceptService().getConcept(drugsConceptId);

		for (Obs obs : Context.getObsService().getObservations(patient, drugsDispensed, false)) {
			if (obs != null) {		
				
					
					drugsHistory.add(obs);
				
				
			}
		}
		return drugsHistory;
	}
}