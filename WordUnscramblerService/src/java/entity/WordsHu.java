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
@Table(name = "words_hu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WordsHu.findAll", query = "SELECT w FROM WordsHu w"),
    @NamedQuery(name = "WordsHu.findById", query = "SELECT w FROM WordsHu w WHERE w.id = :id"),
    @NamedQuery(name = "WordsHu.findByWord", query = "SELECT w FROM WordsHu w WHERE w.word = :word"),
    @NamedQuery(name = "WordsHu.findByLength", query = "SELECT w.word FROM WordsHu w WHERE LENGTH(w.word) > :length")})
public class WordsHu implements Serializable {

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

    public WordsHu() {
    }

    public WordsHu(Integer id) {
        this.id = id;
    }

    public WordsHu(Integer id, String word) {
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
        if (!(object instanceof WordsHu)) {
            return false;
        }
        WordsHu other = (WordsHu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WordsHu[ id=" + id + " ]";
    }
    
}
