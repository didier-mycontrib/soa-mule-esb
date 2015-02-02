package tp.proposition_pret;

import javax.jws.Oneway;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface PretAnnexeService {
	@Oneway
	public void buildAndSendServicesAnnexesPret(@WebParam(name="propPret")PropositionPret propPret,
											    @WebParam(name="email")String email);
	//exemple de service annexe: offre assurance, ...
}
