package tp.proposition_pret;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import tp.miniBankWS.calcul.CalculEmprunt;
import tp.minibankWS.services.GestionTauxFrais;

@WebService(endpointInterface="tp.proposition_pret.PropPretService")
public class PropPretServiceBean implements PropPretService{
	
    private String calculEmpruntUrl;//url du service externe "calculEmprunt" à invoquer
    private String gestionTauxFraisUrl;//url du service externe "gestionTauxFrais" à invoquer
    private String pretAnnexeServiceUrl;//url du service secondaire "pretAnnexeService" à déclencher
	
	private CalculEmprunt calculEmpruntProxy = null;
	private GestionTauxFrais gestionTauxFraisProxy = null;
	private PretAnnexeService pretAnnexeServiceProxy = null;
	

	@Override
	public PropositionPret getPropPret(double montant,int nb_mois) {
		PropositionPret propPret = new PropositionPret();
		propPret.setMontant(montant);propPret.setNb_mois(nb_mois);propPret.setOrganismePret("bank_xy");
		if(calculEmpruntProxy==null) 
			initProxyCalculEmprunt();
		if(gestionTauxFraisProxy==null)
			initProxyGestionTauxFrais();
		//orchestration élémentaire (en java) 
		double taux_mens_pct = gestionTauxFraisProxy.getTauxInteretCourant(nb_mois); //appel ws1
		propPret.setTaux_mens_pct(taux_mens_pct);
		propPret.setMensualite(calculEmpruntProxy.getMensualite(montant, nb_mois, taux_mens_pct));//appel ws2
		propPret.setFraisDossier(gestionTauxFraisProxy.getFraisDossier(montant)); //appel ws3
		
		System.out.println("getPropPret(montant="+montant+",nb_mois="+nb_mois+") return " + propPret);//trace faculative
		return propPret;
	}

	@Override
	public PropositionPret getPropPretWithEmail(double montant,int nb_mois, String email) {
		PropositionPret propPret = getPropPret(montant,nb_mois);
		if(pretAnnexeServiceProxy==null)
			initProxyPretAnnexeService();
		pretAnnexeServiceProxy.buildAndSendServicesAnnexesPret(propPret, email);
		return propPret;
	}
	
		//injection
		public void setCalculEmpruntUrl(String calculEmpruntUrl) {
			this.calculEmpruntUrl = calculEmpruntUrl;
		}

		//injection
		public void setGestionTauxFraisUrl(String gestionTauxFraisUrl) {
			this.gestionTauxFraisUrl = gestionTauxFraisUrl;
		}
	

		//injection
		public void setPretAnnexeServiceUrl(String pretAnnexeServiceUrl) {
			this.pretAnnexeServiceUrl = pretAnnexeServiceUrl;
		}

		public void initProxyCalculEmprunt(){
			    Service service=null;
				QName SERVICE_NAME = new QName("http://calcul.miniBankWS.tp/", "CalculEmpruntImplService");
				QName PORT_NAME = new QName("http://calcul.miniBankWS.tp/", "CalculEmpruntImplPort");
				service = Service.create(SERVICE_NAME);		//javax.xml.ws.Service
				// Endpoint Address
				//this.calculEmpruntUrl = "http://localhost:8080/wsBank/services/calculEmprunt";
				// Add a port to the Service , javax.xml.ws.soap.SOAPBinding
				service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING,calculEmpruntUrl);

				calculEmpruntProxy = (CalculEmprunt) service.getPort(PORT_NAME, CalculEmprunt.class);		
		}
		
		public void initProxyGestionTauxFrais(){
		    Service service=null;
			QName SERVICE_NAME = new QName("http://services.minibankWS.tp/", "GestionTauxFraisImplService");
			QName PORT_NAME = new QName("http://services.minibankWS.tp/", "GestionTauxFraisImplPort");
			service = Service.create(SERVICE_NAME);		//javax.xml.ws.Service
			// Endpoint Address
			//this.gestionTauxFraisUrl = "http://localhost:8080/wsBank/services/gestionTauxFrais";
			// Add a port to the Service , javax.xml.ws.soap.SOAPBinding
			service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING,gestionTauxFraisUrl);

			gestionTauxFraisProxy = (GestionTauxFrais) service.getPort(PORT_NAME, GestionTauxFrais.class);		
	}
		
		public void initProxyPretAnnexeService(){
		    Service service=null;
			QName SERVICE_NAME = new QName("http://proposition_pret.tp/", "PretAnnexeServiceService");
			QName PORT_NAME = new QName("http://proposition_pret.tp/", "PretAnnexeServicePort");
			service = Service.create(SERVICE_NAME);		//javax.xml.ws.Service
			// Endpoint Address
			//this.calculEmpruntUrl = "http://localhost:8081/annexePretPort";
			// Add a port to the Service , javax.xml.ws.soap.SOAPBinding
			service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING,pretAnnexeServiceUrl);

			pretAnnexeServiceProxy = (PretAnnexeService) service.getPort(PORT_NAME, PretAnnexeService.class);		
	}


}
