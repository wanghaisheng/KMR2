/*
 * TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
 * Copyright (c) 2008, Nationwide Health Information Network (NHIN) Connect. All rights reserved.
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * - Neither the name of the NHIN Connect Project nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *    END OF TERMS AND CONDITIONS
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil.navy.med.dzreg.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kim
 */
@Entity
@Table(name = "DZ_TYPE")
@NamedQueries({
  @NamedQuery(name = "DzType.findAll", query = "SELECT d FROM DzType d"),
  @NamedQuery(name = "DzType.findByDztypeId", query = "SELECT d FROM DzType d WHERE d.dztypeId = :dztypeId"),
  @NamedQuery(name = "DzType.findByDescr", query = "SELECT d FROM DzType d WHERE d.descr = :descr"),
  @NamedQuery(name = "DzType.findByInsertedDt", query = "SELECT d FROM DzType d WHERE d.insertedDt = :insertedDt")
})
public class DzType implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "DZTYPE_ID")
  private Integer dztypeId;
  @Column(name = "DESCR")
  private String descr;
  @Column(name = "INSERTED_DT")
  @Temporal(TemporalType.DATE)
  private Date insertedDt;
  //@OneToMany(cascade = CascadeType.ALL, mappedBy = "dzType", fetch = FetchType.EAGER)
  //private Collection<DzReg> dzRegCollection;

  public DzType() {
  }

  public DzType(Integer dztypeId) {
    this.dztypeId = dztypeId;
  }

  public Integer getDztypeId() {
    return dztypeId;
  }

  public void setDztypeId(Integer dztypeId) {
    this.dztypeId = dztypeId;
  }

  public String getDescr() {
    return descr;
  }

  public void setDescr(String descr) {
    this.descr = descr;
  }

  public Date getInsertedDt() {
    return insertedDt;
  }

  public void setInsertedDt(Date insertedDt) {
    this.insertedDt = insertedDt;
  }

//  public Collection<DzReg> getDzRegCollection() {
//    return dzRegCollection;
//  }
//
//  public void setDzRegCollection(Collection<DzReg> dzRegCollection) {
//    this.dzRegCollection = dzRegCollection;
//  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (dztypeId != null ? dztypeId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof DzType)) {
      return false;
    }
    DzType other = (DzType) object;
    if ((this.dztypeId == null && other.dztypeId != null) || (this.dztypeId != null && !this.dztypeId.equals(other.dztypeId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "DzType[dztypeId=" + dztypeId + "]";
  }

}
