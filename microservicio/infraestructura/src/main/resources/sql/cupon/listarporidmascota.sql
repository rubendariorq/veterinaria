select id, codigo_cupon, valor_descuento, fecha_vigencia, id_mascota
from cupon_descuento
where id_mascota = :id
and fecha_vigencia > now()