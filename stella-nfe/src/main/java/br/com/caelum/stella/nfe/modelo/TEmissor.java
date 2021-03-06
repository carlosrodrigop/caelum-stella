//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.04.23 at 03:29:23 PM BRT 
//

package br.com.caelum.stella.nfe.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TEmissor", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = { "uf", "cnpj", "cnpjMatriz",
        "ie", "dFe" })
public class TEmissor {

    @XmlElement(name = "UF", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected TUf uf;
    @XmlElement(name = "CNPJ", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String cnpj;
    @XmlElement(name = "CNPJMatriz", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String cnpjMatriz;
    @XmlElement(name = "IE", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String ie;
    @XmlElement(name = "DFe", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
    protected List<DFe> dFe;

    public TUf getUF() {
        return uf;
    }

    public void setUF(final TUf value) {
        uf = value;
    }

    public String getCNPJ() {
        return cnpj;
    }

    public void setCNPJ(final String value) {
        cnpj = value;
    }

    public String getCNPJMatriz() {
        return cnpjMatriz;
    }

    public void setCNPJMatriz(final String value) {
        cnpjMatriz = value;
    }

    public String getIE() {
        return ie;
    }

    public void setIE(final String value) {
        ie = value;
    }

    public List<DFe> getDFe() {
        if (dFe == null) {
            dFe = new ArrayList<DFe>();
        }
        return dFe;
    }

    public void setDFe(final List<DFe> list) {
        dFe = list;

    }

}
