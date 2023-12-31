package team.TRS.rampantskies.block.entity;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.utility.Lang;
import com.simibubi.create.foundation.utility.LangBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class AirIntakeBlockEntity extends KineticBlockEntity {
    float intake;
    float thrust;

    public AirIntakeBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        boolean added = false;

        if (!IRotate.StressImpact.isEnabled())
            return added;
        float stressAtBase = calculateStressApplied();
        if (Mth.equal(stressAtBase, 0))
            return added;

        Lang.translate("gui.goggles.kinetic_stats")
                .forGoggles(tooltip);

        addStressImpactStats(tooltip, stressAtBase);

        LangBuilder ms = Lang.translate("generic.unit.meters_per_second");
        LangBuilder n = Lang.translate("generic.unit.newton");

        Lang.translate("gui.goggles.jet_engine.info")
                .forGoggles(tooltip);

        Lang.translate("gui.goggles.air_intake.air_flow")
                .add(Lang.number(intake)
                        .space()
                        .add(ms)
                        .style(ChatFormatting.GREEN))
                .style(ChatFormatting.GRAY)
                .forGoggles(tooltip, 1);

        Lang.translate("gui.goggles.gas_nozzle.thrust")
                .add(Lang.number(thrust)
                        .space()
                        .add(n)
                        .style(ChatFormatting.LIGHT_PURPLE))
                .style(ChatFormatting.GRAY)
                .forGoggles(tooltip, 1);

        return true;

    }


    public void setThrust(float newThrust) {
        thrust = newThrust;
    }

    //gtt related stuff
    public void setIntake(float newIntake){intake = newIntake;}
}
