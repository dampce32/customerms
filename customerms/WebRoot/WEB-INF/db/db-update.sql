insert into t_right(rightId,parentRightId,rightPKCode,rightCode,rightName,rightUrl,array,isLeaf,rightKind,status)
select 
	a.rightId,a.parentRightId,a.rightPKCode,a.rightCode,a.rightName,a.rightUrl,a.array,a.isLeaf,a.rightKind,a.`status`
from new.t_right a
left join t_right b on a.rightId = b.rightId
where b.rightId is null;

insert into t_roleright(rightId,roleId,status)
select a.rightId,1 roleId,1
from new.t_right a
left join t_roleright b on a.rightId = b.rightId and b.roleId = 1 
where b.roleRightId is null ;


