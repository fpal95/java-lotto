package lotto.view;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.lotto.Numbers;
import lotto.domain.lotto.Rank;

public class OutputView {

    private static final String SPLITTER = ", ";

    private static final String MESSAGE_BOUGHT_TICKET = "개를 구매했습니다.";
    private static final String MESSAGE_TICKET_START = "[";
    private static final String MESSAGE_TICKET_END = "]";
    private static final String MESSAGE_STATISTICS_TITLE = "당첨 통계";
    private static final String MESSAGE_STATISTICS_DIVIDER = "---------";
    private static final String MESSAGE_RESULT_START = "총 수익률은 ";
    private static final String MESSAGE_RESULT_END = "입니다.";
    private static final String MESSAGE_RANK_START = "개 일치 (";
    private static final String MESSAGE_RANK_BONUS_START = "개 일치, 보너스 볼 일치 (";
    private static final String MESSAGE_RANK_MIDDLE = "원) - ";
    private static final String MESSAGE_RANK_BONUS_END = "개";

    private OutputView() {}

    public static void printBuyingTickets(final int amount) {
        System.out.println(amount + MESSAGE_BOUGHT_TICKET);
    }

    public static void printLottoTicket(final Numbers numbers) {
        System.out.print(MESSAGE_TICKET_START);
        System.out.print(
            numbers.get().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(SPLITTER))
        );
        System.out.println(MESSAGE_TICKET_END);
    }

    public static void printResult(final BigDecimal calculateRatio) {
        System.out.println(MESSAGE_RESULT_START + calculateRatio + MESSAGE_RESULT_END);
    }

    public static void printStatistics(final Map<Rank, Integer> matchForEachTickets) {
        System.out.println(MESSAGE_STATISTICS_TITLE);
        System.out.println(MESSAGE_STATISTICS_DIVIDER);

        Arrays.stream(Rank.values())
            .forEach(rank -> printRank(rank, matchForEachTickets));
    }

    private static void printRank(final Rank rank, final Map<Rank, Integer> matchForEachTickets) {
        if (rank == Rank.NONE) {
            return;
        }

        if (rank == Rank.SECOND) {
            printBonusRank(rank, matchForEachTickets);
            return;
        }

        System.out.println(rank.getMatches() + MESSAGE_RANK_START + rank.getPrize() + MESSAGE_RANK_MIDDLE + matchForEachTickets.getOrDefault(rank, 0) + MESSAGE_RANK_BONUS_END);
    }

    private static void printBonusRank(final Rank rank, final Map<Rank, Integer> matchForEachTickets) {
        System.out.println(rank.getMatches() + MESSAGE_RANK_BONUS_START + rank.getPrize() + MESSAGE_RANK_MIDDLE + matchForEachTickets.getOrDefault(rank, 0) + MESSAGE_RANK_BONUS_END);
    }
}
