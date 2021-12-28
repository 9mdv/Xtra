package com.github.andreyasadchy.xtra.model.chat

interface ChatMessage {
    val id: String?
    val userId: String?
    val userName: String?
    val displayName: String?
    val message: String?
    val color: String?
    val isAction: Boolean
    val isReward: Boolean
    val emotes: List<TwitchEmote>?
    val badges: List<Badge>?
    var globalBadges: List<TwitchBadge>?
}
