package uniquecardborders.patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.Settings;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RenderPortraitPatches {
    private static TextureAtlas ucbCardUiAtlas;
    public static TextureAtlas.AtlasRegion CARD_FRAME_ATTACK_BASIC;
    public static TextureAtlas.AtlasRegion CARD_FRAME_ATTACK_SPECIAL;
    public static TextureAtlas.AtlasRegion CARD_FRAME_SKILL_BASIC;
    public static TextureAtlas.AtlasRegion CARD_FRAME_SKILL_SPECIAL;
    public static TextureAtlas.AtlasRegion CARD_FRAME_POWER_BASIC;
    public static TextureAtlas.AtlasRegion CARD_FRAME_POWER_SPECIAL;
    public static TextureAtlas.AtlasRegion CARD_BANNER_BASIC;
    public static TextureAtlas.AtlasRegion CARD_BANNER_SPECIAL;
    public static TextureAtlas.AtlasRegion CARD_FRAME_ATTACK_BASIC_L;
    public static TextureAtlas.AtlasRegion CARD_FRAME_ATTACK_SPECIAL_L;
    public static TextureAtlas.AtlasRegion CARD_FRAME_SKILL_BASIC_L;
    public static TextureAtlas.AtlasRegion CARD_FRAME_SKILL_SPECIAL_L;
    public static TextureAtlas.AtlasRegion CARD_FRAME_POWER_BASIC_L;
    public static TextureAtlas.AtlasRegion CARD_FRAME_POWER_SPECIAL_L;
    public static TextureAtlas.AtlasRegion CARD_BANNER_BASIC_L;
    public static TextureAtlas.AtlasRegion CARD_BANNER_SPECIAL_L;

    public static Method renderHelperMethod;
    public static Method popupRenderHelperMethod;
    public static Field popupCardField;
    public static Color renderColor = Color.WHITE.cpy();
    
    static {
        ucbCardUiAtlas = new TextureAtlas(Gdx.files.internal("cardui/uniquebordercardui.atlas"));
        CARD_FRAME_ATTACK_BASIC = ucbCardUiAtlas.findRegion("512/frame_attack_uncommon");
        CARD_FRAME_ATTACK_SPECIAL = ucbCardUiAtlas.findRegion("512/frame_attack_rare");
        CARD_FRAME_SKILL_BASIC = ucbCardUiAtlas.findRegion("512/frame_skill_uncommon");
        CARD_FRAME_SKILL_SPECIAL = ucbCardUiAtlas.findRegion("512/frame_skill_rare");
        CARD_FRAME_POWER_BASIC = ucbCardUiAtlas.findRegion("512/frame_power_uncommon");
        CARD_FRAME_POWER_SPECIAL = ucbCardUiAtlas.findRegion("512/frame_power_rare");
        CARD_BANNER_BASIC = ucbCardUiAtlas.findRegion("512/banner_uncommon");
        CARD_BANNER_SPECIAL = ucbCardUiAtlas.findRegion("512/banner_rare");
        CARD_FRAME_ATTACK_BASIC_L = ucbCardUiAtlas.findRegion("1024/frame_attack_uncommon");
        CARD_FRAME_ATTACK_SPECIAL_L = ucbCardUiAtlas.findRegion("1024/frame_attack_rare");
        CARD_FRAME_SKILL_BASIC_L = ucbCardUiAtlas.findRegion("1024/frame_skill_uncommon");
        CARD_FRAME_SKILL_SPECIAL_L = ucbCardUiAtlas.findRegion("1024/frame_skill_rare");
        CARD_FRAME_POWER_BASIC_L = ucbCardUiAtlas.findRegion("1024/frame_power_uncommon");
        CARD_FRAME_POWER_SPECIAL_L = ucbCardUiAtlas.findRegion("1024/frame_power_rare");
        CARD_BANNER_BASIC_L = ucbCardUiAtlas.findRegion("1024/banner_uncommon");
        CARD_BANNER_SPECIAL_L = ucbCardUiAtlas.findRegion("1024/banner_rare");

        try {
            renderHelperMethod = AbstractCard.class.getDeclaredMethod("renderHelper", new Class[] { SpriteBatch.class, Color.class, TextureAtlas.AtlasRegion.class, float.class, float.class });
            renderHelperMethod.setAccessible(true);
            popupRenderHelperMethod = SingleCardViewPopup.class.getDeclaredMethod("renderHelper", new Class[] { SpriteBatch.class, float.class, float.class, TextureAtlas.AtlasRegion.class });
            popupRenderHelperMethod.setAccessible(true);
            popupCardField = SingleCardViewPopup.class.getDeclaredField("card");
            popupCardField.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    @SpirePatch(
        clz=AbstractCard.class,
        method="renderAttackPortrait"
    )
    public static class AttackPatch {
        public static void Postfix(AbstractCard c, SpriteBatch sb, float x, float y) {
            try {
                switch (c.rarity) {
                    case BASIC:
                        renderHelperMethod.invoke(c, new Object[] { sb, renderColor, CARD_FRAME_ATTACK_BASIC, x, y });
                        return;
                    case SPECIAL:
                        renderHelperMethod.invoke(c, new Object[] { sb, renderColor, CARD_FRAME_ATTACK_SPECIAL, x, y });
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SpirePatch(
        clz=AbstractCard.class,
        method="renderSkillPortrait"
    )
    public static class SkillPatch {
        public static void Postfix(AbstractCard c, SpriteBatch sb, float x, float y) {
            try {
                switch (c.rarity) {
                    case BASIC:
                        renderHelperMethod.invoke(c, new Object[] { sb, renderColor, CARD_FRAME_SKILL_BASIC, x, y });
                        return;
                    case SPECIAL:
                        renderHelperMethod.invoke(c, new Object[] { sb, renderColor, CARD_FRAME_SKILL_SPECIAL, x, y });
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SpirePatch(
        clz=AbstractCard.class,
        method="renderPowerPortrait"
    )
    public static class PowerPatch {
        public static void Postfix(AbstractCard c, SpriteBatch sb, float x, float y) {
            try {
                switch (c.rarity) {
                    case BASIC:
                        renderHelperMethod.invoke(c, new Object[] { sb, renderColor, CARD_FRAME_POWER_BASIC, x, y });
                        return;
                    case SPECIAL:
                        renderHelperMethod.invoke(c, new Object[] { sb, renderColor, CARD_FRAME_POWER_SPECIAL, x, y });
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SpirePatch(
        clz=AbstractCard.class,
        method="renderBannerImage"
    )
    public static class BannerPatch {
        public static void Postfix(AbstractCard c, SpriteBatch sb, float drawX, float drawY) {
            try {
                switch (c.rarity) {
                    case BASIC:
                        renderHelperMethod.invoke(c, new Object[] { sb, renderColor, CARD_BANNER_BASIC, drawX, drawY });
                        return;
                    case SPECIAL:
                        renderHelperMethod.invoke(c, new Object[] { sb, renderColor, CARD_BANNER_SPECIAL, drawX, drawY });
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
    }
    @SpirePatch(
        clz=SingleCardViewPopup.class,
        method="renderFrame"
    )
    public static class FrameLargePatch {
        public static void Postfix(SingleCardViewPopup popup, SpriteBatch sb) {
            try {
                AbstractCard c = ((AbstractCard)popupCardField.get(popup));
                switch (c.type) {
                    case ATTACK:
                        switch (c.rarity) {
                            case BASIC:
                                popupRenderHelperMethod.invoke(popup, new Object[] { sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, CARD_FRAME_ATTACK_BASIC_L });
                                return;
                            case SPECIAL:
                                popupRenderHelperMethod.invoke(popup, new Object[] { sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, CARD_FRAME_ATTACK_SPECIAL_L});
                                return;
                        }
                        return;
                    case SKILL:
                        switch (c.rarity) {
                            case BASIC:
                                popupRenderHelperMethod.invoke(popup, new Object[] { sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, CARD_FRAME_SKILL_BASIC_L });
                                return;
                            case SPECIAL:
                                popupRenderHelperMethod.invoke(popup, new Object[] { sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, CARD_FRAME_SKILL_SPECIAL_L});
                                return;
                        }
                        return;
                    case POWER:
                        switch (c.rarity) {
                            case BASIC:
                                popupRenderHelperMethod.invoke(popup, new Object[] { sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, CARD_FRAME_POWER_BASIC_L });
                                return;
                            case SPECIAL:
                                popupRenderHelperMethod.invoke(popup, new Object[] { sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, CARD_FRAME_POWER_SPECIAL_L});
                                return;
                        }
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SpirePatch(
        clz=SingleCardViewPopup.class,
        method="renderCardBanner"
    )
    public static class BannerLargePatch {
        public static void Postfix(SingleCardViewPopup popup, SpriteBatch sb) {
            try {
                switch (((AbstractCard)popupCardField.get(popup)).rarity) {
                    case BASIC:
                        popupRenderHelperMethod.invoke(popup, new Object[] { sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, CARD_BANNER_BASIC_L });
                        return;
                    case SPECIAL:
                        popupRenderHelperMethod.invoke(popup, new Object[] { sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, CARD_BANNER_SPECIAL_L});
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}