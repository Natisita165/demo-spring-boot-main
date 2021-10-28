package bo.edu.ucb.chatbot.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Nome {
    private Integer nomeId;
    private String sobrenome;
    private List<String> nome;
    private Date lastUpdate;

    public Integer getNomeId() {
        return nomeId;
    }

    public void setNomeId(Integer nomeId) {
        this.nomeId = nomeId;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public List<String> getNome() {
        return nome;
    }

    public void setNome(List<String> nome) {
        this.nome = nome;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Nome(){

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nome nome = (Nome) o;
        return nomeId.equals(nome.nomeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeId);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "nombre='" + nome + '\'' +
                ", apellido='" + sobrenome + '\'' +
                ", lastupdate='" + lastUpdate + '\'' +
                '}';
    }


}
