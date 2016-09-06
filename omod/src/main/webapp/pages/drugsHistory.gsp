<%
    ui.decorateWith("appui", "standardEmrPage", [ title: ui.message("drugsHistory.drugsHistory") ])
%>

<script type="text/javascript">
	var breadcrumbs = [
        { icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' },
        { label: "${patientPropts.name}", link: "/" + OPENMRS_CONTEXT_PATH + "/coreapps/clinicianfacing/patient.page?patientId=${patient.uuid}"},
        { label: "${ ui.message('drugsHistory.drugsHistory') }"}
    ];
</script>

${ ui.includeFragment("coreapps", "patientHeader", [ patient: patient ]) }

<div class="info-section">
    <div class="info-header">
        <h3>${ ui.message("drugsHistory.drugsHistory") }</h3>
    </div>
    <table>
    	<tr><th>${ ui.message("drugsHistory.drug") }</th></tr>
    <div class="info-body">
    	<% drugname.each { %>
    		<tr>
	    		<td>${ui.format(it.valueCoded)}</td>
	    		
    		</tr>
    	<% } %>
    </div>
</div>

