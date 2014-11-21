package ouc.jeep.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ouc.jeep.dao.ManagerDao;
import ouc.jeep.model.Manager;

@Service("managerService")
public class ManagerService {
	
	private ManagerDao managerDao;
	public ManagerDao getManagerDao() {
		return managerDao;
	}
	@Resource
	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}
       
    public int managerCount(){
        return managerDao.getAllManager().size();
    }
    
	public void add(Manager manager) {
		managerDao.add(manager);
	}
    
	public Manager load(int id) {
		return managerDao.load(id);
	}

}

