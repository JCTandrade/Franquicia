package com.franquicia.constant;

public class Constant {
    public class Message{
        public static String OPERACION_EXITO = "La operacion se realizado de manera correcta";
        public static String FRANQUICIA_GUARDADA = "Se ha guardado la franquicia";
        public static String FRANQUICIA_ACTUALIZADA = "Se ha actualizado la franquicia";
        public static  String ERROR_CREANDO = "Se ha presentado un error al crear la franquicia.";
        public static  String ERROR_CONSULTADO = "Se ha presentado un error al consultar %s.";
        public static  String CONSULTA_EXITOSA= "Se ha consultado de manera correcta.";
        public static  String NO_DATA= "No se ha encontrado informacion";
        public static  String ERROR_DATA= "Error: la información suministrada no coincide.";

        public static String NO_EXISTE_FRANQUICIA = "La franquicia con el ID ingresado no existe";
        public static String INACTIVA_FRANQUICIA = "La franquicia se encuentra es estado inactiva y no se le puede asociar ninguna sucursal";

        public static String NO_CAMBIAR = "No se puede cambiar de franquicia una sucursal";
        public static String SUCURSAL_ASIGNADA = "No se puede eliminar la compañía porque tiene sucursales asignadas.";
    }
}
