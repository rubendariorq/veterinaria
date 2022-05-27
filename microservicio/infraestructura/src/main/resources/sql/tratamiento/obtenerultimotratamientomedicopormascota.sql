select  id, codigo_tratamiento, fecha_inicio, fecha_fin, tipo_tratamiento, id_mascota, id_servicio, valor
from tratamiento
where id_mascota = :id_mascota
and tipo_tratamiento = :tipo_tratamiento
order by fecha_fin desc
limit 1