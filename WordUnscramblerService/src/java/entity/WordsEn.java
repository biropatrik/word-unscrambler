/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Patrik
 */
@Entity
@Table(name = "words_en")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WordsEn.findAll", query = "SELECT w FROM WordsEn w"),
    @NamedQuery(name = "WordsEn.findById", query = "SELECT w FROM WordsEn w WHERE w.id = :id"),
    @NamedQuery(name = "WordsEn.findByWord", query = "SELECT w FROM WordsEn w WHERE w.word = :word"),
    @NamedQuery(name = "WordsEn.findByLength", query = "SELECT w.word FROM WordsEn w WHERE LENGTH(w.word) > :length")
})
public class WordsEn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "word")
    private String word;

    public WordsEn() {
    }

    public WordsEn(Integer id) {
        this.id = id;
    }

    public WordsEn(Integer id, String word) {
        this.id = id;
        this.word = word;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WordsEn)) {
            return false;
        }
        WordsEn other = (WordsEn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WordsEn[ id=" + id + " ]";
    }
    
}
