/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.interfaces;

import java.util.ArrayList;

import dao.entidad.Informaciondetalle;
import dao.entidad.InformaciondetalleId;

/**
 *
 * @author Manuel Machuca
 */
public interface IInformacionDetalleDAO {
	public abstract void insertarInformacionDetalle(Informaciondetalle informaciondetalle);
    public abstract boolean noExisteRegistrado(InformaciondetalleId id);
    public abstract ArrayList<Informaciondetalle> listarCursosInfoDetalleDeAlumno(Integer idAlumno);
    public abstract void eliminarInfoDetalle(InformaciondetalleId idInfoDetalle);
}
