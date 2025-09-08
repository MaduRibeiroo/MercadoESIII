package unoeste.fipp.mercadofipp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Base64;

@Entity
@Table(name = "foto_anuncio")
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fot_id")
    private Long id;

    @Column(name = "fot_file")
    private byte[] file;

    @Transient
    private String img64;
    @Column(name = "fot_ext")
    private String extensao;

    @ManyToOne
    @JoinColumn(name = "anu_id")
    private Anuncio anuncio;

    public Foto() {
        this(0L, null, null, null);
    }

    public Foto(Long id, byte[] file, String extensao, Anuncio anuncio) {
        this.id = id;
        this.file = file;
        this.anuncio = anuncio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getImg64() {
        if (file != null && file.length > 0 && extensao != null) {
            return "data:" + extensao + ";base64," + Base64.getEncoder().encodeToString(file);
        }
        return null;
    }

    public void setImg64(String img64) {
        this.img64 = img64;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

//    public Anuncio getAnuncio() {
//        return anuncio;
//    }
//
//    public void setAnuncio(Anuncio anuncio) {
//        this.anuncio = anuncio;
//    }
}
