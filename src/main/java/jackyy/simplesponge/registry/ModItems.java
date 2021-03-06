package jackyy.simplesponge.registry;

import jackyy.simplesponge.SimpleSponge;
import jackyy.simplesponge.item.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class ModItems {

    public static ItemSpongeOnAStick spongeOnAStick = new ItemSpongeOnAStick();
    public static ItemMagmaticSpongeOnAStick magmaticSpongeOnAStick = new ItemMagmaticSpongeOnAStick();
    public static ItemEnergizedSpongeOnAStick energizedSpongeOnAStick = new ItemEnergizedSpongeOnAStick();
    public static ItemCompressedSpongeOnAStick compressedSpongeOnAStick = new ItemCompressedSpongeOnAStick();
    public static ItemCompressedMagmaticSpongeOnAStick compressedMagmaticSpongeOnAStick = new ItemCompressedMagmaticSpongeOnAStick();

    public static void init(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                spongeOnAStick,
                magmaticSpongeOnAStick,
                energizedSpongeOnAStick,
                compressedSpongeOnAStick,
                compressedMagmaticSpongeOnAStick,
                new BlockItem(ModBlocks.sponge, new Item.Properties().group(SimpleSponge.TAB)).setRegistryName(ModBlocks.sponge.getRegistryName()),
                new BlockItem(ModBlocks.magmaticSponge, new Item.Properties().group(SimpleSponge.TAB)).setRegistryName(ModBlocks.magmaticSponge.getRegistryName())
        );
    }

}
