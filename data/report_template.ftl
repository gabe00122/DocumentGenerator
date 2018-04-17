
<document>
    <p style="title">
        Peer Evaluation for <v>${FirstName} ${LastName}</v>, PhD
        2017-2018 Academic Year
    </p>

    <p>
        <b>Teaching</b>

	    Dr. <v>${LastName}</v> taught <v>${FallHours}</v> hours (<v>${FallCourses}</v>) in the fall and <v>${SpringHours}</v> hours (<v>${SpringCourses}</v>) in the spring.
        During the 2017-2018 academic year, Dr. <v>${LastName}</v> was classified as research active/inactive. If applicable, additional adjustments to workload are listed here:

        <v>
            ${FallOverload} ${SpringOverload}
        </v>
    </p>

    <p>
        Summaries of student responses on the IDEA instrument are shown in the table below

        Table 1: IDEA Summaries

        Table 2: Student Supervision Outside of Class
    </p>

    <table>
        <tr style="b">
            <tc># Practica/Internships Supervised</tc>
            <tc># MA students supervised</tc>
            <tc># Honors students supervised</tc>
            <tc># Thesis committees</tc>
            <tc># of DIS Credits Supervised</tc>
        </tr>
        <tr style="v">
            <tc>${InternshipNumber!}</tc>
            <tc>${MANumber!}</tc>
            <tc>${HonorsNumber!}</tc>
            <tc>${ThesisCommitteeNumber!}</tc>
            <tc>${DISNumber!}</tc>
        </tr>
    </table>

    <p>
        Dr. <v>${LastName!}</v> developed the following new courses:
        <v>${NewCourses!}</v>

        Dr. <v>${LastName!}</v>'s efforts to improve teaching included:
        <v>${TeachingEfforts!}</v>

        Awards received by Dr. <v>${LastName!}</v>'s students included:
        <v>${StudentAwards!}</v>
    </p>

    <p>
        <b>Research</b>

        Peer-Reviewed Articles and Citations: Dr. <v>${LastName}</v> published <v>${SeniorAuthoredArticles!}</v>
        peer-reviewed journal articles as senior author and co-authored <v>${NonSeniorAuthoredArticles!}</v> peer-reviewed journal articles, the full citations of which are listed here:
        <v>${APACitations!}</v>
    </p>

    <p>
        Conference Presentations: Dr. <v>${LastName}</v> had <v>${ConferencePresentations!}</v> with <v>${UndergradConferenceAuthors!}</v> and <v>${GradConferenceAuthors!}</v>
    </p>

    <p>
        Conference Presentation Citations:
        <v>${ConferenceNames!}</v>
    </p>

    <p>
        Other publications: Dr. <v>${LastName}</v> published
        <v>${BookChapters!}</v>
    </p>

    <p>
        Dr. <v>${LastName}</v> had <v>${UndergradBookAuthors}</v> undergraduate and <v>${GraduateBookAuthors}</v> graduate student co-authors on published work.
    </p>

    <p>
        Project Funding and Collaborations:

        Table 3: Research Funding Summary
    </p>
    <title>
        <tr style="b">
            <tc>External Grants funding requested Total $</tc>
            <tc>External Grant funding received Total $</tc>
            <tc>UNCW funding Total $</tc>
        </tr>
        <tr style="v">
            <tc>${RequestedExternalGrant!}</tc>
            <tc>${ReceivedExternalGrant!}</tc>
            <tc>${InternalGrant!}</tc>
        </tr>
    </title>

    <p>
        <v>${GrantProposalsFunded!}</v>
    </p>


</document>
