package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.MinLevelException;
import units.Archer;
import units.Unit;

public class ArcheryRange extends MilitaryBuilding {
	private int downgradeGoldBack;

	public int getDowngradeGoldBack() {
		return downgradeGoldBack;
	}

	public void setDowngradeGoldBack(int downgradeGoldBack) {
		this.downgradeGoldBack = downgradeGoldBack;
	}

	public ArcheryRange() {
		super(1500, 800,400);
		this.downgradeGoldBack = 0;
		
	}

	@Override
	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
		if(isCoolDown())
			throw new BuildingInCoolDownException("Building is cooling down, please wait for the next turn");
		if(getCurrentRecruit()==getMaxRecruit())
			throw new MaxRecruitedException("Max recruited units reached, please wait till next turn. ");
		setCurrentRecruit(getCurrentRecruit()+1);
		if(getLevel()==1)
			return new Archer(1, 60, 0.4, 0.5, 0.6);
		
	else if(getLevel()==2)
		return new Archer(2,60,0.4,0.5,0.6);
	else
		return new Archer(3,70,0.5,0.6,0.7);
		
	}

	@Override
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		super.upgrade();
		if(getLevel()==1)
		{
			setLevel(2);
			setUpgradeCost(700);
			setRecruitmentCost(450);
		}
		else if(getLevel()==2)
		{
		setLevel(3);
		setRecruitmentCost(500);
		}
		
	}
	 public void downgrade() throws BuildingInCoolDownException, MinLevelException{
		 if(this.isCoolDown())
			 	throw new BuildingInCoolDownException();
		 if(this.getLevel() == 1)
			 	throw new MinLevelException();
		 if(this.getLevel() == 3) {
			 this.setLevel(2);
			 this.setDowngradeGoldBack(300);
			 this.setUpgradeCost(700);
			 this.setRecruitmentCost(450);
		 }
		 else if(this.getLevel() == 2 ) {
			 this.setLevel(1);
			 this.setDowngradeGoldBack(500);
			 this.setUpgradeCost(800);
			 this.setRecruitmentCost(400);
		 }
		 this.setCoolDown(true);
	 }


}
