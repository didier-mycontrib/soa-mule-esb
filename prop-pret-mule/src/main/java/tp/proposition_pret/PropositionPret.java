package tp.proposition_pret;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import tp.util.MyJaxbDateAdapter;

@XmlRootElement(name="PropositionPret")
public class PropositionPret {
	
	private String organismePret;//ex: "banque xy" 

	private Date date=new Date();
	
	private double montant;
	private int nb_mois;
	private double taux_mens_pct;
	private double mensualite;
	private double fraisDossier;
	
	
	@Override
	public String toString() {
		return "PropositionPret [organismePret=" + organismePret + ", date="
				+ date + ", montant=" + montant + ", nb_mois=" + nb_mois
				+ ", taux_mens_pct=" + taux_mens_pct + ", mensualite="
				+ mensualite + ", fraisDossier=" + fraisDossier + "]";
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public int getNb_mois() {
		return nb_mois;
	}
	public void setNb_mois(int nb_mois) {
		this.nb_mois = nb_mois;
	}
	public double getTaux_mens_pct() {
		return taux_mens_pct;
	}
	public void setTaux_mens_pct(double taux_mens_pct) {
		this.taux_mens_pct = taux_mens_pct;
	}
	public double getMensualite() {
		return mensualite;
	}
	public void setMensualite(double mensualite) {
		this.mensualite = mensualite;
	}
	public double getFraisDossier() {
		return fraisDossier;
	}
	public void setFraisDossier(double fraisDossier) {
		this.fraisDossier = fraisDossier;
	}
	public String getOrganismePret() {
		return organismePret;
	}
	public void setOrganismePret(String organismePret) {
		this.organismePret = organismePret;
	}
	
	@XmlElement
    @XmlSchemaType(name = "date") //type xs:date (not xs:datetime) into generated wsdl
	@XmlJavaTypeAdapter(value=MyJaxbDateAdapter.class)//value
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
