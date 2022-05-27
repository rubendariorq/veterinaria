select id, codigo_tratamiento, fecha_inicio, fecha_fin, tipo_tratamiento, id_mascota, id_servicio, valor
from tratamiento
where id_mascota = :id_mascota
and fecha_fin > now()
and tipo_tratamiento = :tipo_tratamiento