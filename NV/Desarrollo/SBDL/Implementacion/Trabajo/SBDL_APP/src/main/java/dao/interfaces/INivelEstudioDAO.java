/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import java.util.ArrayList;

import dao.entidad.NivelEstudio;

/**
 *
 * @author Manuel Machuca
 */
public interface INivelEstudioDAO {
    
    public abstract NivelEstudio buscarNivelEstudio(int nivelEstudio);
    
    public abstract ArrayList<NivelEstudio> obtenerListaNivelEstudio();
}
