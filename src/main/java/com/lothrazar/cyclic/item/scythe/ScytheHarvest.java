package com.lothrazar.cyclic.item.scythe;

import com.lothrazar.cyclic.base.ItemBase;
import com.lothrazar.cyclic.net.PacketHarvesting;
import com.lothrazar.cyclic.registry.PacketRegistry;
import com.lothrazar.cyclic.util.UtilItemStack;
import com.lothrazar.cyclic.util.UtilShape;
import java.util.List;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class ScytheHarvest extends ItemBase {

  public ScytheHarvest(Properties properties) {
    super(properties);
  }

  public static IntValue RADIUS;

  public List<BlockPos> getShape(BlockPos pos, int radius) {
    return UtilShape.squareHorizontalFull(pos, radius);
  }

  @Override
  public ActionResultType onItemUse(ItemUseContext context) {
    BlockPos pos = context.getPos();
    Direction side = context.getFace();
    if (side != null) {
      pos = pos.offset(side);
    }
    PlayerEntity player = context.getPlayer();
    if (player.world.isRemote) {
      int radius = (player.isCrouching()) ? RADIUS.get() / 2 : RADIUS.get();
      PacketRegistry.INSTANCE.sendToServer(new PacketHarvesting(pos, radius));
    }
    player.swingArm(context.getHand());
    UtilItemStack.damageItem(player, context.getItem());
    return super.onItemUse(context);
  }
}
