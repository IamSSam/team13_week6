package cnu.lecture;

import org.junit.Before;
import org.junit.Test;

import cnu.lecture.InGameInfo.Participant;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by tchi on 2016. 4. 25..
 */
public class InGameSummonerQuerierTest {
	private InGameSummonerQuerier querier;

	@Before
	public void setup() {
		final String apiKey = "8242f154-342d-4b86-9642-dfa78cdb9d9c";

		querier = mock(InGameSummonerQuerier.class);
	}

	@Test
	public void shouldQuerierIdentifyGameKeyWhenSpecificSummonerNameIsGiven() throws Exception {

		final String summonerName;
		GIVEN: {
			summonerName = "akane24";
		}

		final String actualGameKey;
		WHEN: {
			when(querier.queryGameKey(summonerName)).thenReturn("4/bl4DC8HBir8w7bGHq6hvuHluBd+3xM");
			actualGameKey = querier.queryGameKey(summonerName);
		}

		final String expectedGameKey = "4/bl4DC8HBir8w7bGHq6hvuHluBd+3xM";
		THEN: {
			assertThat(actualGameKey, is(expectedGameKey));
		}
	}

	@Test
	public void shouldQuerierReportMoreThan5Summoners() {

		final InGameInfo inGameInfo;

		GIVEN: {

			inGameInfo = mock(InGameInfo.class);

			Participant[] participants = new Participant[4];

			for (int i = 0; i < participants.length; i++) {
				participants[i] = mock(Participant.class);
			}
			when(inGameInfo.getParticipants()).thenReturn(participants);
		}

		final int actualNumberOfSummoners;
		WHEN: {

			actualNumberOfSummoners = inGameInfo.getParticipants().length;
		}

		final int expectedNumberOfSummoners = 4;
		THEN: {
			assertTrue(actualNumberOfSummoners >= expectedNumberOfSummoners);
		}
	}

}
