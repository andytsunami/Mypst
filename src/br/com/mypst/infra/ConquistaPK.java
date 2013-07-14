package br.com.mypst.infra;

import java.io.Serializable;

public class ConquistaPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6850168054463388759L;
	private Long trofeu;
	private Long usuario;
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof ConquistaPK){
			ConquistaPK conquistaPk = (ConquistaPK) obj;
 
            if(!conquistaPk.getTrofeu().equals(trofeu)){
                return false;
            }
 
            if(!conquistaPk.getUsuario().equals(usuario)){
                return false;
            }
 
            return true;
        }
 
        return false;
	}
	
	@Override
	public int hashCode() {
		return trofeu.hashCode() + usuario.hashCode();
	}
	
	public ConquistaPK(){
		
	}
	
	public Long getTrofeu() {
		return trofeu;
	}
	public void setTrofeu(Long idTrofeu) {
		this.trofeu = idTrofeu;
	}
	public Long getUsuario() {
		return usuario;
	}
	public void setUsuario(Long idUsuario) {
		this.usuario = idUsuario;
	}
	
	

}
