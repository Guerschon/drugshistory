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
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		Integer dateDrugsConceptId=1276;
		Concept drugsDispensed = Context.getConceptService().getConcept(drugsConceptId);
		Concept dateDispensed = Context.getConceptService().getConcept(dateDrugsConceptId);
		List<Concept> conceptList = new ArrayList<Concept>();
		List<Encounter> encounterList = new ArrayList<Encounter>();

		for (Obs obs0 : Context.getObsService().getObservations(patient, drugsDispensed, false)) {
			Obs obs=Obs.newInstance(obs0);
			if (obs0 != null) {	
				for (Obs obs1 : Context.getObsService().getObservations(patient, dateDispensed, false)) {
					if (obs1 != null) {
						if(obs0.getObsGroupId()==obs1.getObsGroupId()){
							//je capture dans obs la date de dispensation du medicament
							obs.setObsDatetime(obs1.getObsDatetime());
							conceptList.add(obs0.getValueCoded());
							encounterList.add(obs0.getEncounter());
							//j'ajoute la date de dispensation
							drugsHistory.add(obs);
						}
						else if(conceptList.contains(obs0.getValueCoded()) && encounterList.contains(obs0.getEncounter()));
						else
						{
							conceptList.add(obs0.getValueCoded());
							encounterList.add(obs0.getEncounter());
							//j'ajoute la date de prescription si la date de dispensation n'est pas disponible
							drugsHistory.add(obs);
							
						} 
						
						} 
					/*else {
						DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
						Date d=new Date();
						try {
							d = df.parse("0001-01-01 00:00:00");
							
							} catch (final ParseException e) {
							
							e.printStackTrace();
							
							}
						obs.setObsDatetime(d);
						drugsHistory.add(obs);
						}*/
					
					
				} 
			/*if(!conceptList.contains(obs0.getValueCoded())) {
				conceptList.add(obs0.getValueCoded());
				drugsHistory.add(obs);
				
			} else;*/
				
			} 
				
			}
		return drugsHistory;
		}
}