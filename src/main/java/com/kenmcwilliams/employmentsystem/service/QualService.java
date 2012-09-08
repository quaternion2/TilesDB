/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.service;

import com.kenmcwilliams.employmentsystem.orm.Qual;
import com.kenmcwilliams.employmentsystem.orm.QualLine;
import java.util.List;

/**
 *
 * @author ken
 */
public interface QualService {
    //list all quals
    List<Qual> listQuals();
    Qual getQual(int id);
    QualLine getQualLine(int id);
    void updateQualLine(QualLine qualLine);
    void addQualLine(int qualId, QualLine qualLine);
    void addQual(Qual qual);
    void updateQual(Qual qual);
    void deleteQual(int id);
}
