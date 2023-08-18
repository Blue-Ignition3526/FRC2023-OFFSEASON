package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NavX;

public class AutoBalance extends CommandBase {
  private NavX navx = null; // Navx contiene girosensor
  private DriveTrain driveTrain = null; // mover

  public AutoBalance(NavX navx, DriveTrain driveTrain){
    this.navx = navx;
    this.driveTrain = driveTrain;

    addRequirements(driveTrain, navx);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double balance =  navx.getBalance(); // valor girosensor

    if (Math.abs(balance) > 3) { // si no esta blanseado en rango de -3 a 3
      if (balance < 0) { // si es para un lado
        driveTrain.arcadeDrive(0.3, 0);
      } else if (balance > 0) { // si es para el otro
        driveTrain.arcadeDrive(-0.3, 0);
      }
    }else{
        driveTrain.arcadeDrive(0, 0);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
