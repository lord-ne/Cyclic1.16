package com.lothrazar.cyclicmagic.item.projectile;
import com.lothrazar.cyclicmagic.IHasRecipe;
import com.lothrazar.cyclicmagic.entity.projectile.EntityMagicNetFull;
import com.lothrazar.cyclicmagic.entity.projectile.EntityMagicNetEmpty;
import com.lothrazar.cyclicmagic.entity.projectile.EntityThrowableDispensable;
import com.lothrazar.cyclicmagic.entity.projectile.EntityTorchBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ItemProjectileMagicNet extends BaseItemProjectile implements IHasRecipe {
  public EntityThrowableDispensable getThrownEntity(World world, double x, double y, double z) {
    return new EntityTorchBolt(world, x, y, z);
  }
  @Override
  public void addRecipe() {
//    GameRegistry.addShapelessRecipe(new ItemStack(this, 1), new ItemStack(Blocks.TALLGRASS, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.TORCH));
//    GameRegistry.addShapelessRecipe(new ItemStack(this, 1), new ItemStack(Blocks.LEAVES, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.TORCH));
//    GameRegistry.addShapelessRecipe(new ItemStack(this, 1), new ItemStack(Blocks.LEAVES2, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.TORCH));
  }
  @Override
  void onItemThrow(ItemStack held, World world, EntityPlayer player, EnumHand hand) {
    if(held.getTagCompound() != null && held.getTagCompound().hasKey("id")){

      this.doThrow(world, player, hand, new EntityMagicNetFull(world, player,held));
      held.getTagCompound().removeTag("id");
      held.setTagCompound(null);
      held = null;
    }
    else{

      this.doThrow(world, player, hand, new EntityMagicNetEmpty(world, player));
    }
  }
}
