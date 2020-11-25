package blueduck.christmas_wish_in_a_bottle.registry;

import blueduck.christmas_wish_in_a_bottle.block.BottleLauncherBlock;
import blueduck.christmas_wish_in_a_bottle.item.WishInBottleItem;
import blueduck.jellyfishing.jellyfishingmod.JellyfishingMod;
import blueduck.jellyfishing.jellyfishingmod.blocks.JellyBlock;
import blueduck.jellyfishing.jellyfishingmod.items.SpatulaItem;
import blueduck.jellyfishing.jellyfishingmod.registry.BlockItemBase;
import blueduck.jellyfishing.jellyfishingmod.registry.JellyfishingBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "christmas_wish_in_a_bottle");
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "christmas_wish_in_a_bottle");

    public static final RegistryObject<Block> BOTTLE_LAUNCHER = BLOCKS.register("bottle_launcher", () -> new BottleLauncherBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BROWN).sound(SoundType.METAL).hardnessAndResistance(2.0F, 2.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2).notSolid()));
    public static final RegistryObject<Item> BOTTLE_LAUNCHER_ITEM = ITEMS.register("bottle_launcher", () -> new BlockItem(BOTTLE_LAUNCHER.get(), new Item.Properties().group(ItemGroup.MISC)));

    public static final RegistryObject<Item> WISH_IN_A_BOTTLE = ITEMS.register("christmas_wish_in_a_bottle", () -> new WishInBottleItem(new Item.Properties().group(ItemGroup.MISC)));


    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
